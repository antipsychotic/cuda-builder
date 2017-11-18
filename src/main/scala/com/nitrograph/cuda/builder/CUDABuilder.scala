package com.nitrograph.cuda.builder

import com.nitrograph.cuda.builder.resource._

object CudaBuilder {
  def main(arguments: Array[String]): Unit = {

    val runtimeResourceTest = new RuntimeResourceTest

    runtimeResourceTest.check
    /**
      * I want:
      * 1. Build Input Set {
            Resource Manager {
              Input Generator {
                Input[
                  Constant Input Metadata: Fixed for any Input Generator
                  Target Location: Application Name, Build Name, Set Name
                ];
                Resources[
                  One block of M elements of T type
                ];
                Output[
                  Target: $root/application/build/set/name(input).input
                ];
              }
            }
          }
      * 2. Build {
            Resource Manager {
              Input Reader {
                Resources[
                  N blocks of M elements of T type
                ];
              }
              Host Interface; {
                Resources[
                  N blocks of M elements of T type
                ];
              }
              Host Module; {
                Resources[
                  N blocks of M elements of T type
                ];
              }
              Kernel Interface; {
                Resources[
                  N blocks of M elements of T type
                ];
              }
              Kernel module; {
                Cuda Resources[
                  N blocks of M elements of T type
                ];
              }
              Output Fetcher; {
                Resources[
                  N blocks of M elements of T type
                ];
              }
              Output Printer; {
                Resources[
                  N blocks of M elements of T type
                ];
              }
            }
          }
    **/
  }
}
