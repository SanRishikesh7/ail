happy(yolanda).
listens2Music(mia).

listens2Music(yolanda) :- happy(yolanda).
playsAirGuitar(mia) :- listens2Music(mia).
playsAirGuitar(yolanda) :- listens2Music(yolanda).

#clauses
# 1. happy(yolanda).
# 2. listens2Music(mia).
# 3. listens2Music(yolanda) :- happy(yolanda).
# 4. playsAirGuitar(mia) :- listens2Music(mia).
# 5. playsAirGuitar(yolanda) :- listens2Music(yolanda).
#how many predicates are there and what are they
# there are 3 predicates happy/1, listens2Music/1, playsAirGuitar/1