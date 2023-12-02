symptom(charlie,fever).
symptom(charlie,rash).
symptom(charlie,head_ache).
symptom(charlie,runny_nose).
hypothesis(Patient,measles):-symptom(Patient,fever),symptom(Patient,cough),
                         symptom(Patient,conjuctivites),symptom(Patient,runny_nose),
                         symptom(Patient,rash).
hypothesis(Patient,german_measles):-symptom(Patient,fever),
                                  symptom(Patient,head_ache), symptom(Patient,runny_nose),
                                  symptom(Patient,rash).
hypothesis(Patient,flu):-symptom(Patient,fever), symptom(Patient,head_ache),
                                  symptom(Patient,body_ache), symptom(Patient,conjuctivites),
                                  symptom(Patient,chills), symptom(Patient,sore_throat),
                                  symptom(Patient,runny_nose), symptom(Patient,cough).
hypothesis(Patient,chicken_pox) :- symptom(Patient,fever), symptom(Patient,chills),
                                  symptom(Patient,body_ache), symptom(Patient,rash).
# how many clauses are there
# there are total 8 clauses


#how many predicates are there and what are they

# there are total 2 predicates symptom/2 and hypothesis/2

# how many facts and how many rules are there in the system
# there are 4 facts and 4 rules