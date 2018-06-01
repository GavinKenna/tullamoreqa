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
      parallel {
        stage('Verify') {
          steps {
            sh 'mvn verify'
          }
        }
        stage('Archive Artifacts') {
          steps {
            archiveArtifacts(artifacts: '*', onlyIfSuccessful: true)
          }
        }
      }
    }
    stage('Integration Tests') {
      steps {
        sh 'mvn clean install -Ptest'
      }
    }
    stage('Archive Artifacts') {
      steps {
        archiveArtifacts(artifacts: '*', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
  }
}