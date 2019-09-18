package com.bandaddict.Converter;

import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.Entity.Sheet;
import org.springframework.core.convert.converter.Converter;

public class SheetToSheetDTOConverter implements Converter<Sheet, SheetDTO> {

    @Override
    public SheetDTO convert(final Sheet sheet) {
        final SheetDTO sheetDTO = new SheetDTO();

        sheetDTO.setTitle(sheet.getTitle());
        sheetDTO.setName(sheet.getName());
        sheetDTO.setCreatedAt(sheet.getCreatedAt());
        sheetDTO.setInstrument(sheet.getInstrument());

        return sheetDTO;
    }
}
