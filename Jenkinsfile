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
    stage('Integration Tests') {
      steps {
        sh 'mvn clean install -Ptest'
      }
    }
    stage('Archive Artifacts') {
      steps {
        archiveArtifacts(artifacts: '*/target/*.jar', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
  }
}