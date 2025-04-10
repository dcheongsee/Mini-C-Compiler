class Course {
    int credit;
}

class VirtualCourse extends Course {
    char zoomLink[200];
}

void main() {
    class Course course;
    // explicit cast is used
    course = (class Course) new class VirtualCourse();
    course.credit = 10;
}
