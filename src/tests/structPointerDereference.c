struct Sensor {
    int type;
    char id[16];
    struct Calibration* cal;
};

struct Calibration {
    int offset;
    void* metadata;
};

void adjust(struct Sensor* s) {
    int offset_temp;
    char* desc;

    offset_temp = (*(struct Calibration*)((*s).cal)).offset;
    (*(struct Calibration*)((*s).cal)).offset = offset_temp + 1;

    if ((*s).type == 2) {
        desc = (char*)((*(struct Calibration*)((*s).cal)).metadata);
        desc[0] = 'A';
    }
}

int main() {
    struct Calibration c;
    struct Sensor s1;
    char buf[10];

    c.offset = 1;
    c.metadata = (void*)0;
    s1.type = 2;
    s1.cal = &c;
    c.metadata = &buf[0];

    adjust(&s1);
    return sizeof(struct Sensor) + sizeof(struct Calibration);
}