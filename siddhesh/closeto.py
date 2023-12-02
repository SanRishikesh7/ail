import numpy as np
import matplotlib.pyplot as plt

def triangular_membership(x, center, width):
    left = center - width/2
    right = center + width/2
    
    if x <= left or x >= right:
        return 0
    elif left < x <= center:
        return (x - left) / (center - left)
    elif center < x < right:
        return (right - x) / (right - center)

# Define parameters for the triangular fuzzy set
center = 10
width = 3  # Adjust the width based on how "close" you want the numbers to be

# Generate x values over the universe of discourse (0 to 20)
x_values = np.linspace(0, 20, 1000)

# Calculate membership values for each x
membership_values = [triangular_membership(x, center, width) for x in x_values]

# Plot the triangular fuzzy set
plt.plot(x_values, membership_values, label=f'Triangular Fuzzy Set ({center}, {width})')
plt.title('Fuzzy Set for Numbers Close to 10')
plt.xlabel('x')
plt.ylabel('Membership Value')
plt.legend()
plt.grid(True)
plt.show()
