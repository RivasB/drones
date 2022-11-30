package com.musala.drones.mapper;

public interface IMapper<I, O> {
    public O map(I in);
}
