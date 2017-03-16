1.在imageloader中，有一个提炼的工厂模式的一个图片加载封装：
    每次添加一个新的imageengin就可以实现不同的图片三方加载的方式.
    get
    ImageloaderPoxyImp.getInstance().setLoader(new ImageloaderEngin()).displayImage("",new ImageView(getApplicationContext()));

