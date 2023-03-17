project(freetime)

cmake_minimum_required(VERSION 3.10)

set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/opencv-mobile-4.6.0-android/sdk/native/jni)
find_package(OpenCV REQUIRED)

set(ncnn_DIR ${CMAKE_SOURCE_DIR}/ncnn-20230223-android-vulkan/${ANDROID_ABI}/lib/cmake/ncnn)
find_package(ncnn REQUIRED)


# openmp
option(COLORGAN_OPENMP "openmp support" ON)
set(USE_OPEN_MP TRUE CACHE BOOL "Set to FALSE to not use OpenMP")
if (USE_OPEN_MP)
    find_package(OpenMP REQUIRED)
    if (OpenMP_FOUND)
        message("OpenMP FOUND")
        set (CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${OpenMP_C_FLAGS}")
        set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${OpenMP_CXX_FLAGS}")
    else ()
        message(FATAL_ERROR "openmp Not Found!")
    endif ()
endif ()

add_library(freetime SHARED scrfdncnn.cpp scrfd.cpp ndkcamera.cpp)
target_link_libraries(freetime ncnn ${OpenCV_LIBS} camera2ndk mediandk)
