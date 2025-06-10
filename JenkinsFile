pipeline {
	agent any

	environment {
		NAMESPACE = "alvarolt17-dev"
		BUILD_NAME = "devsecops-starter"
		DEPLOYMENT_NAME = "jenkins"
	}

	stages {
		stage('Trigger Build in OpenShift'){
			steps {
				sh "oc start-build ${BUILD_NAME} --from-dir=. --follow -n ${NAMESPACE}"
			}
		}

		stage("Deploy to OpenShift") {
			steps {
				sh "oc rollout latest dc/${DEPLOYMENT_NAME} -n ${NAMESPACE}"
			}
		}
	}

	post {
		success {
			echo "Build and deployment successful!"
		}
		failure {
			echo "Build or deployment failed."
		}
		always {
			echo "Pipeline execution completed."
		}
	}
}