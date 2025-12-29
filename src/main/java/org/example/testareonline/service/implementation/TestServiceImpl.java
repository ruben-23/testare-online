package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.*;
import org.example.testareonline.dto.response.*;
import org.example.testareonline.entity.*;
import org.example.testareonline.mapper.IntrebareMapper;
import org.example.testareonline.mapper.OptiuneMapper;
import org.example.testareonline.mapper.TestMapper;
import org.example.testareonline.repository.DomeniuRepository;
import org.example.testareonline.repository.TestRepository;
import org.example.testareonline.repository.UserRepository;
import org.example.testareonline.service.ActiveUsersService;
import org.example.testareonline.service.GuestService;
import org.example.testareonline.service.TestService;
import org.example.testareonline.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final DomeniuRepository domeniuRepository;
    private final TestMapper testMapper;
    private final IntrebareMapper intrebareMapper;
    private final OptiuneMapper optiuneMapper;
    private final UserService userService;
    private final GuestService guestService;
    private final ActiveUsersService activeUsersService;

    @Override
    public TestDTO createTest(TestRequest request) {
        Test test = testMapper.toEntity(request);
        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Domeniu domeniu = domeniuRepository.findById(request.idDomeniu())
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));

        test.setUser(user);
        test.setDomeniu(domeniu);

        return testMapper.toDTO(testRepository.save(test));
    }

    @Override
    @Transactional
    public TestDTO createFullTest(CreateTestRequest request) {
        // 1. Fetch user and domain
        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Domeniu domeniu = domeniuRepository.findById(request.idDomeniu())
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));

        // 2. Map Test entity using mapper
        Test test = testMapper.toEntity(request);
        test.setUser(user);
        test.setDomeniu(domeniu);

        // 3. Map questions and options
        if (request.intrebari() != null) {
            for (CreateIntrebareRequest intrebareReq : request.intrebari()) {
                Intrebare intrebare = intrebareMapper.toEntity(intrebareReq);
                intrebare.setTest(test); // set parent

                if (intrebareReq.optiuni() != null) {
                    for (CreateOptiuneRequest optiuneReq : intrebareReq.optiuni()) {
                        Optiune optiune = optiuneMapper.toEntity(optiuneReq);
                        optiune.setIntrebare(intrebare); // set parent
                        intrebare.getOptiuni().add(optiune);
                    }
                }

                test.getIntrebari().add(intrebare);
            }
        }

        // 4. Save the Test (cascade saves questions and options)
        Test savedTest = testRepository.save(test);

        // 5. Map to DTO for response
        return testMapper.toDTO(savedTest);
    }


    @Override
    public TestDTO getTestById(Integer id) {
        return testRepository.findById(id)
                .map(testMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public List<TestDTO> getAllTeste() {
        return testRepository.findAll().stream()
                .map(testMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TestDTO updateTest(Integer id, TestRequest request) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        test.setTitlu(request.titlu());

        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Domeniu domeniu = domeniuRepository.findById(request.idDomeniu())
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));

        test.setUser(user);
        test.setDomeniu(domeniu);

        return testMapper.toDTO(testRepository.save(test));
    }

    @Override
    @Transactional
    public TestDTO updateFullTest(Integer testId, CreateTestRequest request) {

        // 1. Load existing test
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        // 2. Update basic fields
        test.setTitlu(request.titlu());

        Domeniu domeniu = domeniuRepository.findById(request.idDomeniu())
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));
        test.setDomeniu(domeniu);

        // 3. Clear existing questions (orphanRemoval deletes them)
        test.getIntrebari().clear();

        // 4. Rebuild questions + options from request
        for (CreateIntrebareRequest intrebareReq : request.intrebari()) {

            Intrebare intrebare = intrebareMapper.toEntity(intrebareReq);
            intrebare.setTest(test);

            for (CreateOptiuneRequest optiuneReq : intrebareReq.optiuni()) {
                Optiune optiune = optiuneMapper.toEntity(optiuneReq);
                optiune.setIntrebare(intrebare);
                intrebare.getOptiuni().add(optiune);
            }

            test.getIntrebari().add(intrebare);
        }

        // 5. Save (cascade handles everything)
        Test savedTest = testRepository.save(test);

        return testMapper.toDTO(savedTest);
    }


    @Override
    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }

    public TestFullDTO getFullTestById(Integer id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        List<IntrebareFullDTO> intrebari = test.getIntrebari().stream()
                .map(intrebare -> {
                    // Map options to DTOs
                    List<OptiuneDTO> optiuniDTO = intrebare.getOptiuni().stream()
                            .map(optiune -> new OptiuneDTO(
                                    optiune.getId(),
                                    optiune.getContinut(),
                                    optiune.getPunctaj(),
                                    optiune.getIsCorrect(),
                                    optiune.getIntrebare().getId()
                            ))
                            .collect(Collectors.toList());

                    // Calculate total punctaj: sum of punctaj from CORRECT options

                     int punctajTotal = intrebare.getOptiuni().stream()
                         .filter(Optiune::getIsCorrect)
                         .mapToInt(Optiune::getPunctaj)
                         .sum();

                    return new IntrebareFullDTO(
                            intrebare.getId(),
                            intrebare.getContinut(),
                            intrebare.getTest().getId(),
                            punctajTotal,
                            optiuniDTO
                    );
                })
                .collect(Collectors.toList());

        return TestFullDTO.builder()
                .id(test.getId())
                .titlu(test.getTitlu())
                .dataCrearii(test.getDataCrearii())
                .idUser(test.getUser().getId())
                .idDomeniu(test.getDomeniu().getId())
                .intrebari(intrebari)
                .build();
    }

    /**
     * Handles taking a test, including username generation, adding to active users, and broadcasting.
     * Returns the full test DTO with the final username set.
     */
    @Override
    public TestFullDTO takeTest(Integer id) {
        String finalUsername;
        Integer userId = null;
        // Check authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser");
        if (isAuthenticated) {
            userId = Integer.valueOf(auth.getPrincipal().toString());
            finalUsername = userService.findUsernameById(userId);  // Logged-in user
        }else {
            finalUsername = guestService.generateUniqueRandomUsername(activeUsersService, id);
        }

        // Fetch and return test data
        TestFullDTO test = getFullTestById(id);
        test.setGuestUsername(finalUsername);
        return test;
    }

    public TestResultDTO submitTest(Integer testId, SubmitTestRequest request) {
        List<AnswerSubmission> submissions = request.getAnswers();
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        // Map: questionId -> set of selected option IDs by user
        Map<Integer, Set<Integer>> userAnswersMap = submissions.stream()
                .collect(Collectors.toMap(
                        AnswerSubmission::getQuestionId,
                        sub -> new HashSet<>(sub.getSelectedOptionIds()),
                        (a, b) -> b
                ));

        double earnedScore = 0.0;
        double maxPossibleScore = 0.0;
        int totalQuestions = test.getIntrebari().size();

        for (Intrebare question : test.getIntrebari()) {
            Integer questionId = question.getId();
            Set<Integer> userSelectedIds = userAnswersMap.getOrDefault(questionId, Collections.emptySet());

            for (Optiune optiune : question.getOptiuni()) {
                int optionId = optiune.getId();
                int punctaj = optiune.getPunctaj() != null ? optiune.getPunctaj() : 0; // safe null check
                boolean isSelected = userSelectedIds.contains(optionId);

                // Only award points if the user selected this option
                if (isSelected) {
                    earnedScore += punctaj;
                }

                // Always add to total if it's a correct answer
                if (optiune.getIsCorrect()) {
                    maxPossibleScore += punctaj;
                }
            }
        }

        // Optional: prevent negative score
        if (earnedScore < 0) earnedScore = 0;

        double percentage;
        boolean passed;

        if (maxPossibleScore == 0) {
            percentage = 0.0;
            passed = false;
        } else {
            percentage = (earnedScore / maxPossibleScore) * 100;
            passed = percentage >= 70;
        }

        return new TestResultDTO(earnedScore, maxPossibleScore, totalQuestions, percentage, passed);
    }
}
