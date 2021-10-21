package jobs

import LogTags
import Logger
import domain.services.impl.JtiSchedulingServiceImpl
import org.eclipse.jetty.util.thread.Scheduler.Task
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.omg.CORBA.portable.ApplicationException
import org.quartz.*
import org.quartz.JobBuilder.newJob
import org.quartz.impl.StdSchedulerFactory


class DeleteExpiredJtiJob: Job, KoinComponent {

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
}


