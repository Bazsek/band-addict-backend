package com.bandaddict.Converter;

import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.Entity.Sheet;
import org.springframework.core.convert.converter.Converter;

public class SheetDTOToSheetConverter implements Converter<SheetDTO, Sheet> {

    @Override
    public Sheet convert(final SheetDTO sheetDTO) {
        final Sheet sheet = new Sheet();

        sheet.setName(sheetDTO.getName());
        sheet.setTitle(sheetDTO.getTitle());
        sheet.setInstrument(sheetDTO.getInstrument());
        sheet.setCreatedAt(sheetDTO.getCreatedAt());
        sheet.setCreatedBy(sheetDTO.getCreatedBy());
        sheet.setSheet(sheetDTO.getSheet());

        return sheet;
    }
}
