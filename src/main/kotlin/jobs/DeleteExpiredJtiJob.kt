package jobs

import LogTags
import Logger
import domain.services.impl.JtiSchedulingServiceImpl
import org.quartz.Job
import org.quartz.JobExecutionContext


class DeleteExpiredJtiJob(private val jtiSchedulingServiceImpl: JtiSchedulingServiceImpl): Job {

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

