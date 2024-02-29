job('ejemplo2-job-DSL') {
  description('Job DSL de ejemplo para curso Jenkins')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main') { node ->
      node / gitConfigName('falayg')
      node / gitConfigEmail('alay.gutierrez06@outlook.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job')
    choiceParam('planeta',['Mercurio', 'Venus', 'Tierra', 'Marte'])
    booleanParam('agente', false)
  }
  triggers {
    	cron('H/7 * * * *')
  }
  steps{
    	shell("bash jobscript.sh")
  }
  publishers{
  	mailer('alay.gutierrez06@outlook.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
