package com.nitrograph.cuda.code.memory.operation

import com.nitrograph.cuda.code._
import com.nitrograph.cuda.builder.resource._

enum class UnaryMemoryOperationKind
    extends MemoryOperationKind
object UnaryMemoryOperationKind {
    case Allocation(chunkSize: Int)
    case Deallocation
}

case class UnaryMemoryOperation(
    target: Target,
    kind: UnaryMemoryOperationKind
) extends CUDACode {
    def toCPP: String = {
        target match {
            case Target.Host(memoryChunk) => {
                kind match {
                    case UnaryMemoryOperationKind.Allocation(size) => {
                        s"""
                            chunks->${memoryChunk.name} = malloc(
                                ${memoryChunk.size}
                            );
                        """
                    }
                    case UnaryMemoryOperationKind.Deallocation => {
                        s"""
                            free(
                                chunks->${memoryChunk.name}
                            );
                        """
                    }
                }
            }
            case Target.Device(memoryChunk) => {
                kind match {
                    case UnaryMemoryOperationKind.Allocation(size) => {
                        s"""
                            cudaMalloc(
                                &chunks->${memoryChunk.name},
                                ${memoryChunk.size}
                            );
                        """
                    }
                    case UnaryMemoryOperationKind.Deallocation => {
                        s"""
                            cudaFree(
                                chunks->${memoryChunk.name}
                            );
                        """
                    }
                }
            }
        }
    }
}
