symptom(john, fever).
symptom(john, cough).
symptom(john, shortness_of_breath).
symptom(lisa, headache).
symptom(lisa, nausea).
symptom(lisa, dizziness).
symptom(lisa, fatigue).
symptom(peter, fever).
symptom(peter, body_ache).
symptom(peter, fatigue).
symptom(peter, sore_throat).
symptom(peter, runny_nose).

hypothesis(Patient, covid_19) :- 
    symptom(Patient, fever),
    symptom(Patient, cough),
    symptom(Patient, shortness_of_breath).

hypothesis(Patient, migraine) :- 
    symptom(Patient, headache),
    symptom(Patient, nausea),
    symptom(Patient, dizziness).

hypothesis(Patient, flu) :- 
    symptom(Patient, fever),
    symptom(Patient, body_ache),
    symptom(Patient, fatigue),
    symptom(Patient, sore_throat),
    symptom(Patient, runny_nose).

hypothesis(Patient, common_cold) :- 
    symptom(Patient, runny_nose),
    symptom(Patient, sore_throat),
    symptom(Patient, cough).

hypothesis(Patient, unknown) :-
    writeln('Unable to diagnose. More information is needed.').
