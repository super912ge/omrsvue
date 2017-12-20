# omrs

> A Vue.js project

## Build Setup

``` bash
# Maven downloads and installs npm and node.js
mvn install

# The junit5 dependency for the project currently doesn't have a mvn artifact
# Clone the project to a directory outside the project, then install the compiled jar to your local maven repo
git clone https://github.com/sbrannen/spring-test-junit5.git
cd spring-test-junit5
./gradlew install

# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build
```

For detailed explanation on how things work, consult the [docs for vue-loader](http://vuejs.github.io/vue-loader).
