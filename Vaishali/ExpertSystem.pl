knows(aarya, html).
knows(aarya, css).
knows(aarya, js).

knows(vaishali, java).
knows(vaishali, python).
knows(vaishali, r).

knows(sakshi, java).

company(amazon).
company(PhonePe).

hire(amazon,fullStack).
hire(amazon,machine_learning).
hire(PhonePe,android).

expert(X, fullStack) :- knows(X, html),knows(X, css),knows(X, js).
expert(X, android) :- knows(X,java);knows(X,kotlin);knows(X,flutter).
expert(X, machine_learning) :- knows(X,r);knows(X,python).

recommendation(D, X) :- company(X),hire(X, Y),expert(D, Y).