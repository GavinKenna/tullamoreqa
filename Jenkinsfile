pipeline {
  agent any
  stages {
    stage('CheckOut') {
      steps {
        git(url: 'https://gkenna92@bitbucket.org/gkenna92/tullamoreqa.git', branch: 'master', credentialsId: 'jenkins', poll: true, changelog: true)
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
  }
}