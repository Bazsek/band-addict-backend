package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.PostDTO;
import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.DTO.SongDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.Sheet;
import com.bandaddict.Entity.Song;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Repository.SheetRepository;
import com.bandaddict.Repository.SongRepository;
import com.bandaddict.Service.SongService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private BandRepository bandRepository;
    private ConversionService conversionService;
    private SongRepository songRepository;
    private SheetRepository sheetRepository;

    public SongServiceImpl(final BandRepository bandRepository, final ConversionService conversionService,
                           final SongRepository songRepository, final SheetRepository sheetRepository) {
        this.bandRepository = bandRepository;
        this.conversionService = conversionService;
        this.songRepository = songRepository;
        this.sheetRepository = sheetRepository;
    }


    @Override
    public void createSong(final SongDTO songDTO, final User user) {
        final Band band = bandRepository.findOneById(user.getBand().getId());
        final Song song = conversionService.convert(songDTO, Song.class);
        final List<Song> songList = band.getSongs();

        song.setBand(band);
        songList.add(songRepository.save(song));
        band.setSongs(songList);

        bandRepository.save(band);
    }

    @Override
    public List<SongDTO> getAllSong(final User user) {

        return songRepository.getAllByBand(user.getBand()).stream().
                map(song -> conversionService.convert(song, SongDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SheetDTO> getLatestSheets(){

        return sheetRepository.findAll().stream().
                map(sheet -> conversionService.convert(sheet, SheetDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SheetDTO> searchSheet(final String text) {
        final List<Sheet> all = sheetRepository.findAll();
        final List<SheetDTO> result = new ArrayList<>();

        all.forEach(sheet -> {
            if (sheet.getName().toLowerCase().contains(text.toLowerCase()) || sheet.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    sheet.getInstrument().toLowerCase().contains(text.toLowerCase())) {
                result.add(conversionService.convert(sheet, SheetDTO.class));
            }
        });

        return result;
    }

    @Override
    public void uploadSheet(final SheetDTO sheetDTO, final User user) {
        final Sheet sheet = conversionService.convert(sheetDTO, Sheet.class);
        sheet.setCreatedBy(user);
        sheet.setCreatedAt(new Date());

        sheetRepository.save(sheet);
    }
}
