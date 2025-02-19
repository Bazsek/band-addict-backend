package com.bandaddict.Config;

import com.bandaddict.Converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer implementation
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public final void addCorsMappings(final CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
    }

    @Override
    public final void addFormatters(final FormatterRegistry formatterRegistry){
        formatterRegistry.addConverter(new UserDTOToUserConverter());
        formatterRegistry.addConverter(new UserToUserDTOConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new PostDTOToPostConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new PostToPostDTOConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new InstrumentToInstrumentDTOConverter());
        formatterRegistry.addConverter(new InstrumentDTOTOInstrumentConverter());
        formatterRegistry.addConverter(new BandDTOToBandConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new BandToBandDTOConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new MusicStyleTOMusicStyleDTOConverter());
        formatterRegistry.addConverter(new MusicStyleDTOtoMusicStyleConverter());
        formatterRegistry.addConverter(new SongDTOTOSongConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new SongTOSongDTOConverter());
        formatterRegistry.addConverter(new SheetDTOToSheetConverter());
        formatterRegistry.addConverter(new SheetToSheetDTOConverter());
        formatterRegistry.addConverter(new UserToSearchResponseConverter());
        formatterRegistry.addConverter(new BandToSearchResponseConverter());
        formatterRegistry.addConverter(new PostToSearchResponseConverter());
        formatterRegistry.addConverter(new EventDTOToEventConverter());
        formatterRegistry.addConverter(new EventToEventDTOConverter());
        formatterRegistry.addConverter(new AlbumDTOToAlbumConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new AlbumToAlbumDTOConverter((ConversionService)formatterRegistry));
        formatterRegistry.addConverter(new LyricsDTOToLyricsConverter());
        formatterRegistry.addConverter(new LyricsToLyricsDTOConverter((ConversionService)formatterRegistry));
    }
}
