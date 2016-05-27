# BlackSwan

Creata test project for Black Swan.
Used architecture pattern: MVC
Used design patterns: Singleton

Used lybraries: 

- for the bottom navigation bar
    compile 'com.roughike:bottom-bar:1.3.3'
    
- for the materila horizontal progressbar. (for the devices before lollipop)
    compile 'me.zhanghai.android.materialprogressbar:library:1.0.2'

- for the dependencies
    compile 'com.squareup.dagger:dagger:1.2.0'
    provided 'com.squareup.dagger:dagger-compiler:1.2.0'

- for the network communication
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'com.squareup.okhttp3:logging-interceptor:3.0.1'

- for the image loading, caching
    compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'
    
- for the recyclerview animations
    compile 'jp.wasabeef:recyclerview-animators:2.2.1'
    
Improvement ideas:
- separate the ResultsWrapper for Movies, Series and Peoples subclasses
- create a better, not just a base error handling logic.
- make more api request to get more datas (genres)
- create offline functionality to save the datas into a SQLite DB. (using some ORM libraries , ORMLite, DBFlow)

Tested devices:
LG Nexus 5
LG G5
