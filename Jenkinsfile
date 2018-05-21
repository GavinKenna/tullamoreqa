pipeline {
  agent {
    docker {
      image 'maven'
    }

  }
  stages {
    stage('CheckOut') {
      steps {
        git(url: 'https://gkenna92@bitbucket.org/gkenna92/tullamoreqa.git', branch: '*', credentialsId: 'jenkins', poll: true, changelog: true)
      }
    }
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
    stage('Deploy') {
      steps {
        sh 'mvn deploy'
      }
    }
  }
}