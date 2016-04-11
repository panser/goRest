package ua.org.gostroy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.gostroy.repository.TripRepository;

/**
 * Created by Sergey on 4/11/2016.
 */
@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;


}
