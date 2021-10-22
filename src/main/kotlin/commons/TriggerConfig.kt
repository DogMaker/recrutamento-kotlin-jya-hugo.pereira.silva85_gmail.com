package commons

import application.configuration.EnvironmentConfig
import jobs.DeleteExpiredJtiJob
import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory

class TriggerConfig(
    private val deleteExpiredJtiJob: DeleteExpiredJtiJob,
    private val env: EnvironmentConfig
) {
   private val groupName ="groupJtiDelete"
   private val jobName ="groupJtiDelete"
   private val triggerName ="groupJtiDelete"

    fun start(){
        val schedule = StdSchedulerFactory().scheduler

        val job = JobBuilder.newJob(deleteExpiredJtiJob::class.java)
            .withIdentity(jobName, groupName)
            .build()

        val trigger = TriggerBuilder.newTrigger()
            .withIdentity(triggerName, groupName)
            .withSchedule(CronScheduleBuilder.cronSchedule(env.cronSchedulerTime))
            .build()

        schedule.scheduleJob(job, trigger)
        schedule.start()
        schedule.shutdown(true)
    }
}