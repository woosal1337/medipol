def filter_IIR(x, y=list(), n=-1):
    if n == len(x) - 1:
        return y
    else:
        n += 1
        if x[n] == 1:
            y.append(x[n]) # X(n)
        elif x[n] == 2:
            n = x[n] - (3 * y[n - 1]) # X(n) - 3Y(n-1)
            y.append(n)
        else:
            n = x[n] - (2 * y[n - 1]) - y[n - 2] # X(n) - 2Y(n-1) - Y(n-2)
            y.append(n)

    return filter_IIR(x,y,n)

if __name__=="__main__":
    print(filter_IIR(x=[1,2,0,5,6]))