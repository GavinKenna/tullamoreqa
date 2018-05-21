pipeline {
  agent {
    docker {
      image 'maven'
    }

  }
  stages {
     stage("Checkout") {

          checkout scm

          stash includes: '**', name: 'project'

        }
  }
}