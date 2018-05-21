pipeline {
   agent {
     docker {
       image 'maven'
     }

   }
   stages {
     stage('Build') {
       steps {
         sh 'mvn clean install'
       }
     }
     stage('Verify') {
       steps {
         sh 'mvn verify'
       }
     }
   }
 }