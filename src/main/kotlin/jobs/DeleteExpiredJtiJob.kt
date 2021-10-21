package jobs

import org.quartz.CronScheduleBuilder.cronSchedule
import org.quartz.Job
import org.quartz.JobBuilder.newJob
import org.quartz.JobExecutionContext
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory


class DeleteExpiredJtiJob: Job {

    override fun execute(context: JobExecutionContext?) {
        println("Hello World! - ")
    }
/*
    private val jtiSchedulingServiceImpl: JtiSchedulingServiceImpl by inject()

    private val logger = Logger

    override fun execute(context: JobExecutionContext?) {
        //LoggerContext.initContext(ULID.random())

        logger.info(LogTags.JOB_DELETE_EXPIRED_JTI) {
            "Triggering job for delete expired jti"
        }

        jtiSchedulingServiceImpl.deleteAllExpiredJti()

        logger.info(LogTags.JOB_DELETE_EXPIRED_JTI) {
            "Job for delete expired jti finished"
        }
    }
 */
}

fun teste(){
    //0 0 */3 ? * *	 cron time 3 em 3 horas

    val schedule = StdSchedulerFactory().scheduler

    val job = newJob(DeleteExpiredJtiJob::class.java)
    .withIdentity("job1", "group1")
        .build()

    val trigger = newTrigger()
        .withIdentity("trigger1", "group1")
        .withSchedule(cronSchedule("0/5 * * * * ?"))// adicionar via variavel
        .build()

    schedule.scheduleJob(job, trigger)
    schedule.start() // adicionar no app com injecao do koin
    Thread.sleep(90L * 1000L)
    schedule.shutdown(true) // adicionar no app com injecao do koin

}

