package com.nitrograph.cuda.code.memory.operation

import com.nitrograph.cuda.code._
import com.nitrograph.cuda.builder.resource.memory.chunk._

enum class BinaryMemoryOperationKind
    extends MemoryOperationKind
object BinaryMemoryOperationKind {
    case CopyTo(target: Target)
}

case class BinaryMemoryOperation(
    target: Target,
    kind: BinaryMemoryOperationKind
) extends CUDACode {
    def toCPP: String = {
        target match {
            case Target.Host(sourceChunk) => {
                kind match {
                    case BinaryMemoryOperationKind.CopyTo(target) => {
                        target match {
                            case Target.Host(targetChunk) => {
                                require(
                                    sourceChunk.size == targetChunk.size
                                )
                                s"""
                                    memcpy(
                                        chunks->${targetChunk.name},
                                        chunks->${targetChunk.name},
                                        ${targetChunk.size}
                                    );
                                """
                            }
                            case Target.Device(targetChunk) => {
                                require(
                                    sourceChunk.size == targetChunk.size
                                )
                                s"""
                                    cudaMemcpy(
                                        chunks->${sourceChunk.name},
                                        chunks->${targetChunk.name},
                                        ${targetChunk.size},
                                        cudaMemcpyHostToDevice
                                    );
                                """
                            }
                        }
                    }
                }
            }
            case Target.Device(sourceChunk) => {
                kind match {
                    case BinaryMemoryOperationKind.CopyTo(target) => {
                        target match {
                            case Target.Host(targetChunk) => {
                                require(
                                    sourceChunk.size == targetChunk.size
                                )
                                s"""
                                    cudaMemcpy(
                                        chunks->${sourceChunk.name},
                                        chunks->${targetChunk.name},
                                        ${targetChunk.size},
                                        cudaMemcpyDeviceToHost
                                    );
                                """
                            }
                            case Target.Device(targetChunk) => {
                                require(
                                    sourceChunk.size == targetChunk.size
                                )
                                s"""
                                    cudaMemcpy(
                                        chunks->${sourceChunk.name},
                                        chunks->${targetChunk.name},
                                        ${targetChunk.size},
                                        cudaMemcpyDeviceToDevice
                                    );
                                """
                            }
                        }
                    }
                }
            }
        }
    }
}
