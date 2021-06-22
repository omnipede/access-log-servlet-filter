package io.omnipede.accesslog

interface AccessLogger {

    /**
     * Access log 를 기록하는 인터페이스
     */
    fun log(accessLog: AccessLog?)
}
