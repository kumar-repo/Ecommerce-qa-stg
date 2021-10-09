pipeline {
  agent any
  stages {
    stage('Build') {
          steps {
            echo "Building........."
          }
    }
     stage('Test') {
          steps {
            catchError(buildResult:'SUCCESS', stageResult:'FAILURE')
            {
			bat "mvn clean install"
          }
        }
	}
     
    stage('Publish reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
    }
}