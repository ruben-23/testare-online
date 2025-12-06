
-- TEST ISTORIE --

INSERT INTO teste (titlu, id_user, id_domeniu)
VALUES ('Test Istorie', 1, 1);
SET @test_istorie = LAST_INSERT_ID();

INSERT INTO intrebari (continut, id_test)
VALUES ('Cine a fost primul domnitor al Țării Românești?', @test_istorie);
SET @i1 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                                                                      (@i1, 'Basarab I', 1, 1),
                                                                      (@i1, 'Mircea cel Bătrân', 0, 0),
                                                                      (@i1, 'Radu cel Mare', 0, 0),
                                                                      (@i1, 'Neagoe Basarab', 0, 0);
INSERT INTO intrebari (continut, id_test)
VALUES ('În ce an a avut loc Marea Unire?', @test_istorie);
SET @i2 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@i2, '1918', 1, 1),
                        (@i2, '1848', 0, 0),
                        ( @i2, '1877', 0, 0),
                        ( @i2, '1526', 0, 0);
INSERT INTO intrebari (continut, id_test)
VALUES ('Ce state au făcut parte din Tripla Alianță?', @test_istorie);
SET @i3 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i3, 'Germania', 1, 1),
                        ( @i3, 'Italia', 1, 1),
                        ( @i3, 'Austria-Ungaria', 1, 1),
                        ( @i3, 'Franța', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ( 'Cine a condus Revoluția de la 1821?', @test_istorie);
SET @i4 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i4, 'Tudor Vladimirescu', 1, 1),
                        ( @i4, 'Alexandru Ioan Cuza', 0, 0),
                        ( @i4, 'Horea', 0, 0),
                        ( @i4, 'Avram Iancu', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ( 'În ce an a devenit România republică?', @test_istorie);
SET @i5 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i5, '1947', 1, 1),
                        ( @i5, '1916', 0, 0),
                        ( @i5, '1989', 0, 0),
                        ( @i5, '1923', 0, 0);


INSERT INTO intrebari (continut, id_test) VALUES
    ( 'Ce popoare au fondat Dacia romană?', @test_istorie);
SET @i6 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i6, 'Dacii', 1, 1),
                        ( @i6, 'Romanii', 1, 1),
                        ( @i6, 'Celții', 0, 0),
                        ( @i6, 'Grecii', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ( 'Cine a fost „Luceafărul oilor”?', @test_istorie);
SET @i7 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i7, 'Mihai Viteazul', 0, 0),
                        ( @i7, 'Ștefan cel Mare', 0, 0),
                        ( @i7, 'Avram Iancu', 0, 0),
                        ( @i7, 'Pintea Viteazul', 1, 1);


INSERT INTO intrebari (continut, id_test) VALUES
    ( 'În ce secol s-a format Țara Românească?', @test_istorie);
SET @i8 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i8, 'Secolul XIV', 1, 1),
                        ( @i8, 'Secolul XV', 0, 0),
                        ( @i8, 'Secolul XII', 0, 0),
                        ( @i8, 'Secolul XVI', 0, 0);


INSERT INTO intrebari (continut, id_test) VALUES
    ( 'Cine a fondat Academia Română?', @test_istorie);
SET @i9 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i9, 'Regele Carol I', 1, 1),
                        ( @i9, 'Mihai Eminescu', 0, 0),
                        ( @i9, 'Nicolae Bălcescu', 0, 0),
                        ( @i9, 'I.L. Caragiale', 0, 0);


INSERT INTO intrebari (continut, id_test) VALUES
    ( 'Ce imperiu a cucerit Dacia?', @test_istorie);
SET @i10 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        ( @i10, 'Imperiul Roman', 1, 1),
                        ( @i10, 'Imperiul Otoman', 0, 0),
                        ( @i10, 'Imperiul Bizantin', 0, 0),
                        ( @i10, 'Imperiul Persan', 0, 0);


-- TEST GEOGRAFIE --

INSERT INTO teste (titlu, id_user, id_domeniu)
VALUES ('Test Geografie', 1, 2);
SET @test_geo = LAST_INSERT_ID();

INSERT INTO intrebari (continut, id_test)
VALUES ('Care este cel mai lung fluviu din lume?', @test_geo);
SET @q1 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                                                                      (@q1, 'Nilul', 1, 1),
                                                                      (@q1, 'Amazonul', 0, 0),
                                                                      (@q1, 'Yangtze', 0, 0),
                                                                      (@q1, 'Mississippi', 0, 0);

INSERT INTO intrebari (continut, id_test)
VALUES ('Care este cel mai mare ocean al Terrei?', @test_geo);
SET @q2 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q2, 'Oceanul Pacific', 1, 1),
                        (@q2, 'Oceanul Atlantic', 0, 0),
                        (@q2, 'Oceanul Indian', 0, 0),
                        (@q2, 'Oceanul Arctic', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce țări sunt străbătute de fluviul Dunărea?', @test_geo);
SET @q3 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q3, 'România', 1, 1),
                        (@q3, 'Germania', 1, 1),
                        (@q3, 'Ungaria', 1, 1),
                        (@q3, 'Polonia', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce munți reprezintă cel mai înalt lanț muntos din lume?', @test_geo);
SET @q4 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q4, 'Himalaya', 1, 1),
                        (@q4, 'Anzi', 0, 0),
                        (@q4, 'Alpi', 0, 0),
                        (@q4, 'Caucaz', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este capitala Canadei?', @test_geo);
SET @q5 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q5, 'Ottawa', 1, 1),
                        (@q5, 'Toronto', 0, 0),
                        (@q5, 'Vancouver', 0, 0),
                        (@q5, 'Montreal', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce continente sunt situate în emisfera sudică?', @test_geo);
SET @q6 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q6, 'Australia', 1, 1),
                        (@q6, 'Antarctica', 1, 1),
                        (@q6, 'Africa (parțial)', 1, 1),
                        (@q6, 'Europa', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce desert este cel mai mare din lume?', @test_geo);
SET @q7 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q7, 'Sahara', 0, 0),
                        (@q7, 'Antarctica', 1, 1),
                        (@q7, 'Gobi', 0, 0),
                        (@q7, 'Kalahari', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce stat are cea mai mare populație?', @test_geo);
SET @q8 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q8, 'India', 1, 1),
                        (@q8, 'China', 0, 0),
                        (@q8, 'SUA', 0, 0),
                        (@q8, 'Indonezia', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este cel mai mare lac ca suprafață?', @test_geo);
SET @q9 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q9, 'Marea Caspică', 1, 1),
                        (@q9, 'Lacul Superior', 0, 0),
                        (@q9, 'Lacul Victoria', 0, 0),
                        (@q9, 'Lacul Baikal', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('În ce țară se află Marele Zid Chinezesc?', @test_geo);
SET @q10 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q10, 'China', 1, 1),
                        (@q10, 'India', 0, 0),
                        (@q10, 'Coreea de Nord', 0, 0),
                        (@q10, 'Mongolia', 0, 0);


-- TEST MATEMATICA --

INSERT INTO teste (titlu, id_user, id_domeniu)
VALUES ('Test Matematica', 1, 3);
SET @test_mate = LAST_INSERT_ID();

INSERT INTO intrebari (continut, id_test) VALUES
    ('Cât este 12 × 8?', @test_mate);
SET @q1 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                                                                      (@q1, '96', 1, 1),
                                                                      (@q1, '108', 0, 0),
                                                                      (@q1, '84', 0, 0),
                                                                      (@q1, '88', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este rădăcina pătrată din 144?', @test_mate);
SET @q2 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q2, '12', 1, 1),
                        (@q2, '14', 0, 0),
                        (@q2, '16', 0, 0),
                        (@q2, '10', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care dintre următoarele sunt numere prime?', @test_mate);
SET @q3 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q3, '11', 1, 1),
                        (@q3, '13', 1, 1),
                        (@q3, '15', 0, 0),
                        (@q3, '21', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Cât este 3^4 ?', @test_mate);
SET @q4 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q4, '81', 1, 1),
                        (@q4, '64', 0, 0),
                        (@q4, '27', 0, 0),
                        (@q4, '48', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este rezultatul expresiei: 50 / 2 + 5?', @test_mate);
SET @q5 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q5, '30', 1, 1),
                        (@q5, '20', 0, 0),
                        (@q5, '15', 0, 0),
                        (@q5, '55', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care dintre următoarele sunt multipli ai lui 6?', @test_mate);
SET @q6 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q6, '12', 1, 1),
                        (@q6, '18', 1, 1),
                        (@q6, '20', 0, 0),
                        (@q6, '25', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce unghi are un triunghi echilateral?', @test_mate);
SET @q7 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q7, '60°', 1, 1),
                        (@q7, '90°', 0, 0),
                        (@q7, '45°', 0, 0),
                        (@q7, '120°', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Cât este 1000 - 337?', @test_mate);
SET @q8 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q8, '663', 1, 1),
                        (@q8, '600', 0, 0),
                        (@q8, '737', 0, 0),
                        (@q8, '773', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este valoarea fracției 1/2 + 1/3?', @test_mate);
SET @q9 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q9, '5/6', 1, 1),
                        (@q9, '2/5', 0, 0),
                        (@q9, '1/5', 0, 0),
                        (@q9, '3/4', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este perimetrul unui pătrat cu latura 9 cm?', @test_mate);
SET @q10 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q10, '36 cm', 1, 1),
                        (@q10, '18 cm', 0, 0),
                        (@q10, '27 cm', 0, 0),
                        (@q10, '45 cm', 0, 0);

-- TEST ROMANA --
INSERT INTO teste (titlu, id_user, id_domeniu)
VALUES ('Test Limba Romana', 1, 4);
SET @test_rom = LAST_INSERT_ID();

INSERT INTO intrebari (continut, id_test)  VALUES
    ('Care este antonimul cuvântului “înalt”?', @test_rom);
SET @q1r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q1r, 'Scund', 1, 1),
                        (@q1r, 'Mare', 0, 0),
                        (@q1r, 'Gros', 0, 0),
                        (@q1r, 'Frumos', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care cuvânt este un verb?', @test_rom);
SET @q2r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q2r, 'A alerga', 1, 1),
                        (@q2r, 'Rapid', 0, 0),
                        (@q2r, 'Frumos', 0, 0),
                        (@q2r, 'Bun', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Selectează substantivele proprii:', @test_rom);
SET @q3r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q3r, 'Dunărea', 1, 1),
                        (@q3r, 'Maria', 1, 1),
                        (@q3r, 'masă', 0, 0),
                        (@q3r, 'carte', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este pluralul cuvântului “ou”?', @test_rom);
SET @q4r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q4r, 'ouă', 1, 1),
                        (@q4r, 'oi', 0, 0),
                        (@q4r, 'ouuri', 0, 0),
                        (@q4r, 'ouăle', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce parte de vorbire este cuvântul “frumos”?', @test_rom);
SET @q5r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q5r, 'adjectiv', 1, 1),
                        (@q5r, 'verb', 0, 0),
                        (@q5r, 'adverb', 0, 0),
                        (@q5r, 'substantiv', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care dintre următoarele sunt pronume personale?', @test_rom);
SET @q6r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q6r, 'eu', 1, 1),
                        (@q6r, 'tu', 1, 1),
                        (@q6r, 'acesta', 0, 0),
                        (@q6r, 'cineva', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce mod verbal este verbul „a merge” în forma „merg”?', @test_rom);
SET @q7r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q7r, 'Indicativ', 1, 1),
                        (@q7r, 'Optativ', 0, 0),
                        (@q7r, 'Imperativ', 0, 0),
                        (@q7r, 'Conjunctiv', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este timpul verbului în propoziția: “El a citit cartea”?', @test_rom);
SET @q8r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q8r, 'Perfect compus', 1, 1),
                        (@q8r, 'Imperfect', 0, 0),
                        (@q8r, 'Viitor', 0, 0),
                        (@q8r, 'Mai mult ca perfect', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este complementul direct în propoziția: “Am văzut filmul”?', @test_rom);
SET @q9r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q9r, 'filmul', 1, 1),
                        (@q9r, 'Am', 0, 0),
                        (@q9r, 'văzut', 0, 0),
                        (@q9r, 'eu', 0, 0);
INSERT INTO intrebari (continut, id_test) VALUES
    ('Care propoziție este exclamativă?', @test_rom);
SET @q10r = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@q10r, 'Ce frumos este afară!', 1, 1),
                        (@q10r, 'Afară este frumos.', 0, 0),
                        (@q10r, 'Va fi frumos afară.', 0, 0),
                        (@q10r, 'Este frumos afară?', 0, 0);

-- TEST CHIMIE --

INSERT INTO teste (titlu, id_user, id_domeniu)
VALUES ('Test Chimie', 1, 5);
SET @test_ch = LAST_INSERT_ID();

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este simbolul chimic al oxigenului?', @test_ch);
SET @cq1 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq1, 'O', 1, 1),
                        (@cq1, 'Ox', 0, 0),
                        (@cq1, 'Og', 0, 0),
                        (@cq1, 'On', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este formula apei?', @test_ch);
SET @cq2 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq2, 'H2O', 1, 1),
                        (@cq2, 'H2O2', 0, 0),
                        (@cq2, 'HO', 0, 0),
                        (@cq2, 'O2H', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Selectează metalele:', @test_ch);
SET @cq3 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq3, 'Sodiu', 1, 1),
                        (@cq3, 'Fier', 1, 1),
                        (@cq3, 'Clor', 0, 0),
                        (@cq3, 'Oxigen', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce tip de legătură se formează între doi atomi de oxigen în O2?', @test_ch);
SET @cq4 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq4, 'Legătură covalentă', 1, 1),
                        (@cq4, 'Legătură ionicǎ', 0, 0),
                        (@cq4, 'Legătură metalică', 0, 0),
                        (@cq4, 'Legătură de hidrogen', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce particulă are sarcină pozitivă?', @test_ch);
SET @cq5 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq5, 'Protonul', 1, 1),
                        (@cq5, 'Electronul', 0, 0),
                        (@cq5, 'Neutronul', 0, 0),
                        (@cq5, 'Ionul negativ', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care dintre următoarele sunt gaze nobile?', @test_ch);
SET @cq6 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq6, 'Heliu', 1, 1),
                        (@cq6, 'Neon', 1, 1),
                        (@cq6, 'Clor', 0, 0),
                        (@cq6, 'Azot', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Care este pH-ul unei soluții acide?', @test_ch);
SET @cq7 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq7, 'Mai mic decât 7', 1, 1),
                        (@cq7, 'Egal cu 7', 0, 0),
                        (@cq7, 'Mai mare decât 7', 0, 0),
                        (@cq7, 'Întotdeauna 14', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce element este simbolizat prin Na?', @test_ch);
SET @cq8 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq8, 'Sodiu', 1, 1),
                        (@cq8, 'Azot', 0, 0),
                        (@cq8, 'Argint', 0, 0),
                        (@cq8, 'Neon', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce amestec formează aerul?', @test_ch);
SET @cq9 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq9, 'Amestec de gaze', 1, 1),
                        (@cq9, 'Compus chimic', 0, 0),
                        (@cq9, 'Element pur', 0, 0),
                        (@cq9, 'Soluție', 0, 0);

INSERT INTO intrebari (continut, id_test) VALUES
    ('Ce este HCl?', @test_ch);
SET @cq10 = LAST_INSERT_ID();

INSERT INTO optiuni (id_intrebare, continut, punctaj, is_correct) VALUES
                        (@cq10, 'Acid clorhidric', 1, 1),
                        (@cq10, 'Clorură de hidrogen solidă', 0, 0),
                        (@cq10, 'Bază puternică', 0, 0),
                        (@cq10, 'Sare', 0, 0);
