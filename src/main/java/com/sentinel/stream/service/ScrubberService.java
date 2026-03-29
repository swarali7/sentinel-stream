package com.sentinel.stream.service;

import com.sentinel.stream.model.DataPacket;


public interface ScrubberService {
    DataPacket scrub(DataPacket packet);
}