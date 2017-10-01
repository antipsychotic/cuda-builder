package com.nitrograph.cuda.code

sealed trait CUDACode

enum class MemoryOperationKind
object MemoryOperationKind {
    case Allocation(chunkSize: Int)
    case Deallocation
}

enum class Target
object Target {
    case Host
    case Device
}

case class MemoryOperation(
    target: Target,
    kind: MemoryOperationKind
) extends CUDACode
