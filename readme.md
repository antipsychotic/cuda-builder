# CUDA::Builder { dotty => C++ }

## CodeMap

* `com.nitrograph.cuda`
    * `builder`
        * `resource`
            * `manager`
                * `ResourceManager`
                    * [ ] HostHeapMemory
                    * [ ] DeviceMemory
                    * [ ] Allocate
                    * [ ] Deallocate
            * `RuntimeResource`
                * [ ] Owner
                * [ ] MemoryChunk
                * [ ] ChunkSize
            * `RuntimeResourceTest`
        * `test`
            * `CUDABuilderTestSuite`
        * `CUDABuilder`
    * `code`
        * `dsl`
            * `CUDACodeDSL`
                * [ ] `entity("input") of (2048::floats) in ram chunk`
        * `function`
            * `CUDAFunction`
        * `memory`
            * `operation`
                * `BinaryMemoryOperation`
                * `MemoryOperation`
                * `Target`
                * `UnaryMemoryOperation`
        * `CUDACode`
