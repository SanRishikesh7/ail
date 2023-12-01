import numpy as np
import matplotlib.pyplot as plt

def sgn(net):
    if net >= 0.0:
        return 1.0
    return -1.0

# Input data
inputData = [
    [1, -2, 1.5, 0],
    [1, -0.5, -2, 1.5],
    [0, 1, -1, 1.5]
]

weightO = [1, -1, 0, 0.5]

desiredOutput = [1, -1, 1]

c = 1.0

errorHistory = []
iterations = 0

e = float('inf')

while e != 0.0:
    e = 0.0

    for i in range(len(inputData)):
        input = inputData[i]
        net = 0.0
        output = 0.0

        for j in range(len(input)):
            net += input[j] * weightO[j]

        output = sgn(net)

        error = desiredOutput[i] - output
        if error != 0.0:
            for j in range(len(input)):
                weightO[j] += c * error * input[j]

        e += abs(error)
    
    errorHistory.append(e)
    iterations += 1

    # Plot error graph
    plt.figure()
    plt.plot(range(iterations), errorHistory, marker='o')
    plt.xlabel('Iterations')
    plt.ylabel('Error')
    plt.title('Error Over Iterations')
    plt.grid(True)
    plt.show(block=False)
    plt.pause(0.1)

    print(f'Iteration {iterations}, Error: {e}')

print('=====================================')
print('The Final weights are:', weightO)
print('=====================================')
plt.show()