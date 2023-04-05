#include "Camera.hpp"

namespace gps {

    //Camera constructor
    Camera::Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget, glm::vec3 cameraUp) {
        this->cameraPosition = cameraPosition;
        this->cameraTarget = cameraTarget;
        this->cameraUpDirection = cameraUp;

        glm::vec3 front = cameraTarget - cameraPosition;
        this->cameraFrontDirection = front;
        //TODO - Update the rest of camera parameters
    }

    //void Camera::setCameraFrontDirection(glm::vec3 direction) {
        //this->cameraFrontDirection = direction;
   // }

    //return the view matrix, using the glm::lookAt() function
    glm::mat4 Camera::getViewMatrix() {
        return glm::lookAt(cameraPosition, cameraPosition + cameraFrontDirection, cameraUpDirection);
    }
    //update the camera internal parameters following a camera move event
    void Camera::move(MOVE_DIRECTION direction, float speed) {
        printf("Camera coords: %f %f %f\n", cameraPosition.x, cameraPosition.y, cameraPosition.z);
        if (direction == MOVE_FORWARD) {
            this->cameraPosition += speed * this->cameraFrontDirection;
        }
        if (direction == MOVE_BACKWARD) {
            this->cameraPosition -= speed * this->cameraFrontDirection;
        }
        if (direction == MOVE_LEFT) {
            this->cameraPosition -= glm::normalize(glm::cross(this->cameraFrontDirection, this->cameraUpDirection)) * speed;
        }
        if (direction == MOVE_RIGHT) {
            this->cameraPosition += glm::normalize(glm::cross(this->cameraFrontDirection, this->cameraUpDirection)) * speed;
        }

        //if (direction == MOVE_UP) {
        //    this->cameraPosition += speed * this->cameraUpDirection;
        //}

        //if (direction == MOVE_DOWN) {
        //    this->cameraPosition -= speed * this->cameraUpDirection;
        //}

    }
    void Camera::setPosition(glm::vec3 Par)
    {
        this->cameraPosition = Par;
    }
    void Camera::setTarget(glm::vec3 Par)
    {
        this->cameraTarget = Par;
    }

    //update the camera internal parameters following a camera rotate event
    //yaw - camera rotation around the y axis
    //pitch - camera rotation around the x axis
    void Camera::rotate(float pitch, float yaw) {
        //TODO
        if (pitch > 89.0f)
            pitch = 89.0f;
        if (pitch < -89.0f)
            pitch = -89.0f;

        glm::vec3 direction;
        direction.x = cos(glm::radians(yaw)) * cos(glm::radians(pitch));
        direction.y = sin(glm::radians(pitch));
        direction.z = sin(glm::radians(yaw)) * cos(glm::radians(pitch));
        cameraTarget = cameraPosition + glm::normalize(direction);

        cameraFrontDirection = cameraTarget - cameraPosition;

        cameraRightDirection = glm::cross(cameraFrontDirection, cameraUpDirection);
    }
    void Camera::currPosition() {
        printf("X = %f Y = %f Z = %f\n", cameraPosition.x, cameraPosition.y, cameraPosition.z);
    }
}
