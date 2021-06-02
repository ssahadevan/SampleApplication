import numpy as np

def transform_list(input_list):
    num_list = [float(it) for it in input_list]
    sqrt_list = np.sqrt(num_list)
    return ["sqrt(%d) = %.2f" % (x, y) for (x, y) in zip(num_list, sqrt_list)]

