pipeline { 


	agent any 

	stages {
	   
		stage("Example Build") { 
			steps { 
			    
	        
	            git url: 'https://github.com/lagutierrez13/CS4330TimerDialog.git'

				sh "ls -ltr" 
				
				sh "javac TimerDialog.java"
				
			}
		}
	}
	
	post {
		always {

			archiveArtifacts artifacts: "TimerDialog.class", fingerprint: true
		    archiveArtifacts artifacts: "TimerModel.class", fingerprint: true


		} 
	}

}

