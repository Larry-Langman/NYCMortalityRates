SELECT distinct(sex)
FROM pdeaths;

-- +---------------------------------------------+
-- |                     sex                     |
-- +---------------------------------------------+
-- |  Childbirth and the Puerperium (O00-O09)"   |
-- |  Deformations                               |
-- |  F18-F19                                    |
-- |  G03)"                                      |
-- |  I11                                        |
-- |  I12)"                                      |
-- |  K73)"                                      |
-- |  K73-K74)"                                  |
-- |  Nephrotic Syndrome and Nephrisis (N00-N07  |
-- |  X43                                        |
-- |  X60-X84                                    |
-- |  X85-Y09)"                                  |
-- |  Y87.0)"                                    |
-- |  Y87.1                                      |
-- | F                                           |
-- | Female                                      |
-- | M                                           |
-- | Male                                        |
-- +---------------------------------------------+

--We will have to standardize Male,M,Female,F to M and F


SELECT distinct(race)
FROM pdeaths;

-- +--------------------------------------------+
-- |                    race                    |
-- +--------------------------------------------+
-- |  I13                                       |
-- |  N17-N19                                   |
-- |  X40-X42                                   |
-- |  X45-X59                                   |
-- |  X85-Y09)"                                 |
-- |  Y87.0)"                                   |
-- |  and Chromosomal Abnormalities (Q00-Q99)"  |
-- | Asian and Pacific Islander                 |
-- | Black Non-Hispanic                         |
-- | F                                          |
-- | Female                                     |
-- | Hispanic                                   |
-- | M                                          |
-- | Male                                       |
-- | Non-Hispanic Black                         |
-- | Non-Hispanic White                         |
-- | Not Stated/Unknown                         |
-- | Other Race/ Ethnicity                      |
-- | White Non-Hispanic                         |
-- +--------------------------------------------+

--We will have to standardize Non-Hispanic Black,Black,Non-Hispanic White,White to Black and White


SELECT distinct(cause)
FROM pdeaths;

-- +----------------------------------------------------+
-- |                       cause                        |
-- +----------------------------------------------------+
-- | "Accidents Except Drug Poisoning (V01-X39          |
-- | "Accidents Except Drug Posioning (V01-X39          |
-- | "Assault (Homicide: U01-U02                        |
-- | "Assault (Homicide: Y87.1                          |
-- | "Chronic Liver Disease and Cirrhosis (K70          |
-- | "Congenital Malformations                          |
-- | "Diseases of Heart (I00-I09                        |
-- | "Essential Hypertension and Renal Diseases (I10    |
-- | "Intentional Self-Harm (Suicide: U03               |
-- | "Intentional Self-Harm (Suicide: X60-X84           |
-- | "Meningitis (G00                                   |
-- | "Mental and Behavioral Disorders due to Accidental Poisoning and Other Psychoactive Substance Use (F11-F16 |
-- | "Nephritis                                         |
-- | "Pregnancy                                         |
-- | All Other Causes                                   |
-- | Alzheimer's Disease (G30)                          |
-- | Anemias (D50-D64)                                  |
-- | Aortic Aneurysm and Dissection (I71)               |
-- | Atherosclerosis (I70)                              |
-- | Cerebrovascular Disease (Stroke: I60-I69)          |
-- | Certain Conditions originating in the Perinatal Period (P00-P96) |
-- | Chronic Lower Respiratory Diseases (J40-J47)       |
-- | Diabetes Mellitus (E10-E14)                        |
-- | Human Immunodeficiency Virus Disease (HIV: B20-B24) |
-- | Influenza (Flu) and Pneumonia (J09-J18)            |
-- | Insitu or Benign / Uncertain Neoplasms (D00-D48)   |
-- | Malignant Neoplasms (Cancer: C00-C97)              |
-- | Mental and Behavioral Disorders due to Use of Alcohol (F10) |
-- | Parkinson's Disease (G20)                          |
-- | Peptic Ulcer (K25-K28)                             |
-- | Septicemia (A40-A41)                               |
-- | Tuberculosis (A16-A19)                             |
-- | Viral Hepatitis (B15-B19)                          |
-- +----------------------------------------------------+

--We can see that the same cause may have different tags in the parenthesis.
--We will have to remove the parenthesis and all that is within to standardize causes
