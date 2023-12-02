import matplotlib.pyplot as plt

def sgn(x):
    return 1 if x >= 0 else -1

def main():
    # For the input data
    input_data = [
        [1, -2, 1.5, 0],
        [1, -0.5, -2, 1.5],
        [0, 1, -1, 1.5]
    ]

    # Weight Wo
    weight_o = [-1, 0, 4, 3.5]

    # Desired output for each input
    desired_output = [1, -1, 1]

    # Learning rate (c)
    c = 1.0

    # List to store errors
    errors = []

    e = float('inf')
    epochs = 0

    while e != 0:
        e = 0

        for i in range(len(input_data)):
            input_row = input_data[i]
            net = 0
            for j in range(len(input_row)):
                net += input_row[j] * weight_o[j]

            output = sgn(net)

            e += desired_output[i] - output

            for j in range(len(input_row)):
                weight_o[j] += c * (desired_output[i] - output) * input_row[j]

        # Record the error for this epoch
        errors.append(e)
        epochs += 1

    # Plotting the error
    plt.plot(range(epochs), errors, marker='o')
    plt.title('Error over Epochs')
    plt.xlabel('Epochs')
    plt.ylabel('Error')
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()
