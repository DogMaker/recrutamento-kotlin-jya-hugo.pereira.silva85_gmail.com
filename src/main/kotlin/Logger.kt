object Logger{

    fun info(LogTags: LogTags, function: () -> String) {
        println("OK")
    }

    fun error(LogTags: LogTags, function: () -> String) {
        println("OK")
    }
}

enum class LogTags{
    DELETE_JI,
    CREATE_JTI,
    DATABASE_EXCEPTION,
    DUPLICATE_JTI_EXCEPTION
}