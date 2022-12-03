package com.dev6.core.exception

class  NotFoundException(message:String): Exception(message)


class  JoinException(message:String): Exception(message)

/**
 * 서버 바디 없음
 */
class ServerErrorException(message: String) : Exception(message)

class NotFormatingMethod(message: String) : Exception(message)

/**
 *404 에러
 */
class ServerFailException(message: String) : Exception(message)
/**
 *403 에러
 */
class NotAutorityException(message: String) : Exception(message)
/**
 *409 에러
 */
class AleadyExitException(message: String) : Exception(message)
/**
 *400 에러
 */
class NotCorrectException(message: String) : Exception(message)