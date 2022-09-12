package com.taiwo.process;

import static org.junit.jupiter.api.Assertions.*;

class ProcessesTest {

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void start_process() {
    }

    @org.junit.jupiter.api.Test
    void read_input_file() {
    }

    @org.junit.jupiter.api.Test
    void write_to_file() {
    }

    @org.junit.jupiter.api.Test
    void convert_7_e() {
        String content = "w7 ar7 in this tog7th7r, for7v7r";
        String real_out_content = "we are in this together, forever";
        String fake_out_content = "w7 ar7 in this tog7th7r, for7v7r";
        assertEquals(real_out_content, new Processes().convert_7_e(content));
        assertNotEquals(fake_out_content, new Processes().convert_7_e(content));
    }

    @org.junit.jupiter.api.Test
    void convert_to_upperCase() {
    }

    @org.junit.jupiter.api.Test
    void remove_white_spaces() {
    }

    @org.junit.jupiter.api.Test
    void convert_to_ascii() {
    }

    @org.junit.jupiter.api.Test
    void switch_case_option() {
    }

    @org.junit.jupiter.api.Test
    void for_each_option() {
    }
}