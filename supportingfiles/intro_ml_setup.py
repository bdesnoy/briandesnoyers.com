def plot_decision_boundaries(x, y, c, model, scaler, xlab, ylab):
    h = .5  # step size in the mesh

    x_min, x_max = x.min() - .5, x.max() + .5
    y_min, y_max = y.min() - .5, y.max() + .5
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                        np.arange(y_min, y_max, h))
    Z = model.predict(scaler.transform(np.c_[xx.ravel(), yy.ravel()]))
    Z = Z.reshape(xx.shape)
    plt.contourf(xx, yy, Z, alpha=.5, cmap=plt.cm.Pastel1)

    plt.scatter(x, y, c=c, alpha=0.9)
    plt.xlabel(xlab)
    plt.ylabel(ylab)