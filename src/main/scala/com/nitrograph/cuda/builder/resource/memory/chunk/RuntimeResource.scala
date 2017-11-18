package com.nitrograph.cuda.builder.resource.memory.chunk

import com.nitrograph.cuda.code.variable._
import com.nitrograph.cuda.code._
import com.nitrograph.cuda.code.memory.operation._

trait CUDACODEEntity

sealed trait RuntimeResource

sealed trait DeviceRuntimeResource

sealed trait HostRuntimeResource

case class MemoryChunk(
    name: String,
    owner: Owner,
    size: ChunkSize
) extends RuntimeResource
    with CUDACODEEntity
{ chunk =>
    def allocate: CUDACode = {
        UnaryMemoryOperation(
            owner.toTarget(chunk),
            UnaryMemoryOperationKind.Allocation(
                chunk.size.value
            )
        )
    }

    def deallocate: CUDACode = {
        UnaryMemoryOperation(
            owner.toTarget(chunk),
            UnaryMemoryOperationKind.Deallocation
        )
    }

    def copyTo(target: MemoryChunk): CUDACode = {
        BinaryMemoryOperation(
            owner.toTarget(chunk),
            BinaryMemoryOperationKind.CopyTo(
                Target.Device(target)
            )
        )
    }
}

case class ChunkSize(
    value: Int,
    kind: CUDAVariableKind
) { size =>
    require(size.value > 0)

    override def toString: String = {
        s"""${size.value} * sizeof( \\
            ${size.kind match {
                case DoubleKind => {
                    "double"
                }
                case FloatKind => {
                    "float"
                }
            }} \\
        )
        """
    }
}
