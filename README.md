# Summary

This project is a template for a personal web application. The web application uses Kotlin Multiplatform, KTOR, and Compose for Web (experimental). 
The web application looks different on Desktop and Mobile. 

# HOWTO

The application can be run by 
```
./gradlew run
```

The web application utilizes two configuration files (Both files are in `/Users/Netlight/IntelliJProjects/me/app/src/jvmMain/resources`):

1. `main-content.json` - contains the title, about, and the contact me links. The template supports links from Github, Twitter, or LinkedIn.
2. `projects.json` - contains a list of projects. The projects can also use icons which are shown in the list view. The icon paths are stored inside this file.

# License

Copyright [2023] [Justin Salér]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
