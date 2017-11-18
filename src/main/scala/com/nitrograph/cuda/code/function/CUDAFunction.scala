package com.nitrograph.cuda.code.function

import com.nitrograph.cuda.code._

// TODO: add arguments list
case class CUDAFunctionSelfReference(
    name: String
)

// TODO: add return type and formal arguments list
case class CUDAFunction(
    name: String,
    body: StatementSequence
) extends CUDACode { function =>
    def toCPP: String = {
        s"""
            void ${function.name}(void) {
                ${body.toCPP}
            }
        """
    }
}
