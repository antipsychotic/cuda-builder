package com.nitrograph.cuda.code

import com.nitrograph.cuda.builder.resource.memory.chunk._
import com.nitrograph.cuda.code.function._
import com.nitrograph.cuda.code.variable._


package object dsl {
    sealed trait DSLVariableKind { variableKind =>
        def ::(value: Int): DSLSize = {
            DSLSize(
                value,
                variableKind
            )
        }
    }

    case object DSLFloatKind extends DSLVariableKind

    case object DSLDoubleKind extends DSLVariableKind

    def floats = DSLFloatKind
    def doubles = DSLDoubleKind
    case class DSLSize(
        value: Int,
        kind: DSLVariableKind
    )

    trait DSLOwner

    case object ram extends DSLOwner
    case object gpu extends DSLOwner

    case class DSLPlacedEntity(
        base: DSLSizedEntity,
        owner: DSLOwner
    ) {
        def chunk: MemoryChunk = {
            MemoryChunk(
                base.base.name,
                owner match {
                    case ram => {
                        Owner.Host
                    }
                    case gpu => {
                        Owner.Device
                    }
                },
                ChunkSize(
                    base.size.value,
                    base.size.kind match {
                        case DSLFloatKind => {
                            FloatKind
                        }
                        case DSLDoubleKind => {
                            DoubleKind
                        }
                    }
                )
            )
        }
    }

    case class DSLSizedEntity(
        base: DSLEntity,
        size: DSLSize
    ) { sizedEntity =>
        def in(owner: DSLOwner): DSLPlacedEntity = {
            DSLPlacedEntity(
                sizedEntity,
                owner
            )
        }
    }

    case class DSLEntity(
        name: String
    ) { entity =>
        def of(size: DSLSize): DSLSizedEntity = {
            DSLSizedEntity(
                entity,
                size
            )
        }
    }

    def entity(name: String): DSLEntity = {
        DSLEntity(name)
    }

    // TODO: add formal arguments list
    def function(name: String)(
        body: (
            CUDAFunctionSelfReference,
            StatementSequence
        ) => StatementSequence
    ): CUDAFunction = {
        CUDAFunction(
            name, body(
                CUDAFunctionSelfReference(
                    name
                ),
                CUDACode.Sequence()
            )
        )
    }
}
