import React, { useEffect, useState } from "react";
import { distribute } from "../utils/utils";
import { Button, ButtonGroup } from "@material-ui/core";

export default function SelectInput({
  id,
  text,
  bg = () => "secondary",
  options,
  rows,
  cols,
  multiSelect,
  value,
  alwaysShowPages,
  onSelectOption
}) {
  const [page, setPage] = useState(0);

  useEffect(() => {
    setPage(0);
  }, [options]);

  function isSelected(v) {
    if (!value) {
      return false;
    }
    if (!multiSelect) {
      return id(value) === id(v);
    }
    return !!value.find(val => id(val) === id(v));
  }

  function buildPageButtons(groups, currentPage) {
    if (groups.length > 1 || alwaysShowPages) {
      const btns = [];
      groups.forEach((group, index) => {
        btns.push(
          <Button
            active={currentPage === index}
            onClick={() => setPage(index)}
            text={index + 1}
          />
        );
      });
      return (
        <div className="columns">
          <div className="column">
            <ButtonGroup>{btns}</ButtonGroup>
          </div>
        </div>
      );
    }
    return null;
  }

  const rs = rows || 3;
  const cs = cols || 3;
  const pageSize = rs * cs;

  let optionsList;

  optionsList = distribute(options, pageSize);
  const realRows = Math.max(
    ...optionsList.map(list => Math.ceil(list.length / cs))
  );
  const currentPage = Math.min(page, optionsList.length - 1);

  const pageButtons = buildPageButtons(optionsList, currentPage);

  optionsList = optionsList[currentPage];
  optionsList = distribute(optionsList, cs);
  optionsList = optionsList.map((row, rowIndex) => {
    const buttons = row.map(option => {
      // FIXME
      const color = option.color || "secondary";

      return (
        <div className="column" key={id(option)}>
          <Button
            type={bg}
            color={color}
            active={isSelected(option)}
            kind={bg(option)}
            text={text(option)}
            onClick={() => onSelectOption(option)}
          />
        </div>
      );
    });

    // while (buttons.length < cs) {
    //   buttons.push(<ColumnSpace key={buttons.length} />);
    // }
    return <div className="columns">{buttons}</div>;
  });

  // while (optionsList.length < rs) {
  //   optionsList.push(
  //     <Row dense grow>
  //       <ColumnSpace />
  //     </Row>
  //   );
  // }

  return (
    <div className="columns">
      <div className="column">
        <div className="columns">
          <div className="column">{optionsList}</div>
        </div>
        {pageButtons}
      </div>
    </div>
  );
}
