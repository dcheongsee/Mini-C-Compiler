class Course {
    int credit;
}

class VirtualCourse extends Course {
    char zoomLink[200];
}

void main() {
    class Course course;
    // error, VirtualCourse is a subtype of Course but assignment without explicit cast is illegal
    course = new class VirtualCourse();
}
